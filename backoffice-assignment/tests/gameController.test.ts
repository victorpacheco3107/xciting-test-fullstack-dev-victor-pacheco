import { Request, Response } from 'express';
import { GameService } from '../src/services/gameService';
import { GameController } from '../src/controllers/gameController';
import { GameRepository } from '../src/repositories/gameRepository';
import { Game } from '../src/models/game';

jest.mock('../src/services/gameService');
jest.mock('../src/repositories/gameRepository');

describe('GameController', () => {
    let gameController: GameController;
    let gameService: jest.Mocked<GameService>;
    let req: Partial<Request>;
    let res: Partial<Response>;
    let mockGames: Game[];

    beforeEach(() => {
        const gameRepository = new GameRepository();
        gameService = new GameService(gameRepository) as jest.Mocked<GameService>;
        gameController = new GameController(gameService);
        req = {
            query: {}
        };
        res = {
            render: jest.fn(),
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        };
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
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should render games page with games, total, page, totalPages, search, and limit', async () => {
        req.query = { page: '1', limit: '10', search: 'Game' };
        gameService.getTodayGamesWithFilter.mockResolvedValue({ games: mockGames, total: 1 });

        await gameController.listTodayGamesWithFilter(req as Request, res as Response);

        expect(gameService.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
        expect(res.render).toHaveBeenCalledWith('games', {
            games: mockGames,
            total: 1,
            page: 1,
            totalPages: 1,
            search: 'Game',
            limit: 10
        });
    });

    it('should default page and limit when they are not provided', async () => {
        req.query = { search: 'Game' };
        gameService.getTodayGamesWithFilter.mockResolvedValue({ games: mockGames, total: 1 });

        await gameController.listTodayGamesWithFilter(req as Request, res as Response);

        expect(gameService.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
        expect(res.render).toHaveBeenCalledWith('games', {
            games: mockGames,
            total: 1,
            page: 1,
            totalPages: 1,
            search: 'Game',
            limit: 10
        });
    });

    it('should handle errors and return status 500 with error message', async () => {
        req.query = { page: '1', limit: '10', search: 'Game' };
        const error = new Error('Service error');
        gameService.getTodayGamesWithFilter.mockRejectedValue(error);

        await gameController.listTodayGamesWithFilter(req as Request, res as Response);

        expect(gameService.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.json).toHaveBeenCalledWith({ message: 'Error retrieving games' });
    });

    it('should handle unknown errors and return status 500 with error message', async () => {
        req.query = { page: '1', limit: '10', search: 'Game' };
        const unknownError = 'Unknown error';
        gameService.getTodayGamesWithFilter.mockRejectedValue(unknownError);

        await gameController.listTodayGamesWithFilter(req as Request, res as Response);

        expect(gameService.getTodayGamesWithFilter).toHaveBeenCalledWith(1, 10, 'Game');
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.json).toHaveBeenCalledWith({ message: 'Error retrieving games' });
    });
});
