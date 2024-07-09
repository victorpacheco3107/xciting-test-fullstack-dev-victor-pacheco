import { GameRepository } from '../src/repositories/gameRepository';
import pool from '../src/config/database';
import { Game } from '../src/models/game';
import { QueryResult } from 'pg';

jest.mock('../src/config/database', () => ({
    connect: jest.fn()
}));

describe('GameRepository', () => {
    let gameRepository: GameRepository;
    let mockClient: any;
    let mockGames: Game[];
    let queryResult: QueryResult<Game>;
    let countResult: QueryResult<{ count: string }>;

    beforeEach(() => {
        gameRepository = new GameRepository();
        mockClient = {
            query: jest.fn(),
            release: jest.fn()
        };
        (pool.connect as jest.Mock).mockResolvedValue(mockClient);
        mockGames = [
            {
                id: 1,
                client_ip: '192.168.1.1',
                created_at: new Date(),
                game_code: 'Game1',
                language: 'en',
                referer: 'http://example.com',
                request_url: 'http://localhost/play/game1',
                session_id: 'abc123',
                user_agent: 'Mozilla/5.0',
                redirect_url: 'http://localhost/play/game1?session=abc123&language=en',
            }
        ];
        queryResult = {
            rows: mockGames,
            rowCount: 1,
            command: '',
            oid: 0,
            fields: [],
        };
        countResult = {
            rows: [{ count: '1' }],
            rowCount: 1,
            command: '',
            oid: 0,
            fields: [],
        };
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should retrieve games with filter from the database', async () => {
        mockClient.query
            .mockResolvedValueOnce(queryResult)
            .mockResolvedValueOnce(countResult);

        const result = await gameRepository.getTodayGamesWithFilter(1, 10, 'Game');

        expect(result.games).toEqual(mockGames);
        expect(result.total).toEqual(1);
        expect(mockClient.query).toHaveBeenCalledTimes(2);
        expect(mockClient.release).toHaveBeenCalled();
    });

    it('should log and throw an error when a known error occurs', async () => {
        const error = new Error('Query error');
        mockClient.query.mockRejectedValue(error);
        const consoleErrorSpy = jest.spyOn(console, 'error').mockImplementation();

        await expect(gameRepository.getTodayGamesWithFilter(1, 10, 'Game')).rejects.toThrow(error);
        expect(consoleErrorSpy).toHaveBeenCalledWith('Error executing query:', error.message);
        expect(mockClient.release).toHaveBeenCalled();

        consoleErrorSpy.mockRestore();
    });
});
