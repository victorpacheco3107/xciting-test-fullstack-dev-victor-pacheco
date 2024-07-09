import { GameRepository } from '../repositories/gameRepository';
import { Game } from '../models/game';

export class GameService {
    constructor(private gameRepository: GameRepository) {}

    async getTodayGamesWithFilter(page: number, limit: number, search: string): Promise<{ games: Game[], total: number }> {
        return this.gameRepository.getTodayGamesWithFilter(page, limit, search);
    }
}
