package com.mygdx.minerbob.ui;

/**
 * Created by Алексей on 15.02.2018.
 */

public interface ActionResolver {
    /** Узнать статус входа пользователя */
    public boolean getSignedInGPGS();

    /** Вход */
    public void loginGPGS();

    /** Отправить результат в таблицу рекордов */
    public void submitScoreGPGS(int score);

    /**
     * Разблокировать достижение
     *
     * @param achievementId
     *            ID достижения. Берется из файла games-ids.xml
     */
    public void unlockAchievementGPGS(String achievementId);

    /** Показать Activity с таблицей рекордов */
    public void getLeaderboardGPGS();

    /** Показать Activity с достижениями */
    public void getAchievementsGPGS();

}