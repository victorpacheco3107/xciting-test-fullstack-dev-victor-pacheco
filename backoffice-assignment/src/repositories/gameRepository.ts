import { Game } from '../models/game';
import pool from '../config/database';

export class GameRepository {
    async getTodayGamesWithFilter(page: number, limit: number, search: string): Promise<{ games: Game[], total: number }> {
        const client = await pool.connect();
        try {
            const offset = (page - 1) * limit;
            const res = await client.query(`
                SELECT * 
                FROM session 
                WHERE DATE(created_at) = CURRENT_DATE AND (
                    game_code ILIKE $1 OR 
                    session_id::text ILIKE $1 OR 
                    language ILIKE $1 OR 
                    request_url ILIKE $1
                )
                ORDER BY created_at DESC
                LIMIT $2 OFFSET $3
            `, [`%${search}%`, limit, offset]);

            const countRes = await client.query(`
                SELECT COUNT(*) 
                FROM session 
                WHERE DATE(created_at) = CURRENT_DATE AND (
                    game_code ILIKE $1 OR 
                    session_id::text ILIKE $1 OR 
                    language ILIKE $1 OR 
                    request_url ILIKE $1
                )
            `, [`%${search}%`]);

            return { games: res.rows as Game[], total: parseInt(countRes.rows[0].count, 10) };
        } catch (error) {
            if (error instanceof Error) {
                console.error('Error executing query:', error.message);
            } else {
                console.error('Unknown error:', error);
            }
            throw error;
        } finally {
            client.release();
        }
    }
}
