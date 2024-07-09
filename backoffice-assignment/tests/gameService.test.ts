import { GameService } from '../src/services/gameService';
import { GameRepository } from '../src/repositories/gameRepository';
import { Game } from '../src/models/game';

jest.mock('../src/repositories/gameRepository');

describe('GameService', () => {
    let gameService: GameService;
    let gameRepository: jest.Mocked<GameRepository>;

    beforeEach(() => {
        gameRepository = new GameRepository() as jest.Mocked<GameRepository>;
        gameService = new GameService(gameRepository);
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should return games and total count successfully', async () => {
        const mockGames: Game[] = [
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
            },
        ];

        gameRepository.getTodayGamesWithFilter.mockResolvedValue({ games: mockGames, total: 1 });

        const result = await gameService.getTodayGamesWithFilter(1, 10, 'Game');

        expect(result).toEqual({ games: mockGames, total: 1 });
        expect(gameRepository.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
    });

    it('should throw an error when repository method fails', async () => {
        const error = new Error('Repository error');
        gameRepository.getTodayGamesWithFilter.mockRejectedValue(error);

        await expect(gameService.getTodayGamesWithFilter(1, 10, 'Game')).rejects.toThrow(error);
        expect(gameRepository.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
    });
});
