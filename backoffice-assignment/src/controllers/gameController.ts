import { Request, Response } from 'express';
import { GameService } from '../services/gameService';

export class GameController {
    constructor(private gameService: GameService) {}

    async listTodayGamesWithFilter(req: Request, res: Response): Promise<void> {
        try {
            const page = parseInt(req.query.page as string) || 1;
            const limit = parseInt(req.query.limit as string) || 10;
            const search = req.query.search as string || '';

            const { games, total } = await this.gameService.getTodayGamesWithFilter(page, limit, search);
            const totalPages = Math.ceil(total / limit);

            res.render('games', { games, total, page, totalPages, search, limit });
        } catch (error) {
            if (error instanceof Error) {
                console.error('Error retrieving games:', error.message);
            } else {
                console.error('Unknown error:', error);
            }
            res.status(500).json({ message: 'Error retrieving games' });
        }
    }
}
