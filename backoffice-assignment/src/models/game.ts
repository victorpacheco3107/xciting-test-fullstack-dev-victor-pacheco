export interface Game {
    id: number;
    client_ip: string | null;
    created_at: Date | null;
    game_code: string | null;
    language: string | null;
    referer: string | null;
    request_url: string | null;
    session_id: string | null;
    user_agent: string | null;
    redirect_url: string | null;
}
