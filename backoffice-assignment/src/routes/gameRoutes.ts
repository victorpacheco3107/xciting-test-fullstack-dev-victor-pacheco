import { Router } from 'express';
import { GameRepository } from '../repositories/gameRepository';
import { GameService } from '../services/gameService';
import { GameController } from '../controllers/gameController';

const router = Router();
const gameRepository = new GameRepository();
const gameService = new GameService(gameRepository);
const gameController = new GameController(gameService);

router.get('/games', (req, res) => gameController.listTodayGamesWithFilter(req, res));
router.get('*', (req, res) => res.redirect('/games'));

export default router;
