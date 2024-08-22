class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var m_score1: Int = 0
    private var m_score2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            m_score1 += 1
        else
            m_score2 += 1
    }

    override fun getScore(): String {
        return when {
            isDeuce() -> "Deuce"
            isSamePoint() -> scoreMapToName(m_score1) + "-" + "All"
            isSomeoneWinOrGoingToWin() -> checkWinner(m_score1 - m_score2)
            else -> scoreMapToName(m_score1) + "-" + scoreMapToName(m_score2)
        }
    }

    private fun isDeuce() = isSamePoint() && m_score1 >= 3
    private fun isSamePoint() = m_score1 == m_score2
    private fun isSomeoneWinOrGoingToWin() = m_score1 >= 4 || m_score2 >= 4

    private fun checkWinner(minusResult: Int) = when {
        minusResult == 1 -> "Advantage player1"
        minusResult == -1 -> "Advantage player2"
        minusResult >= 2 -> "Win for player1"
        else -> "Win for player2"
    }

    private fun scoreMapToName(tempScore: Int) = when (tempScore) {
        0 -> "Love"
        1 -> "Fifteen"
        2 -> "Thirty"
        3 -> "Forty"
        else -> ""
    }
}
