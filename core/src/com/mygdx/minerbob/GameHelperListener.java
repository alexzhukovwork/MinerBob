package com.mygdx.minerbob;

/**
 * Created by Алексей on 15.02.2018.
 */

public interface GameHelperListener {
    /**
     * Вызывается при неудачной попытке входа. В этом методе можно показать
     * пользователю кнопку «Sign-in» для ручного входа
     */
    void onSignInFailed();

    /** Вызывается при удачной попытке входа */
    void onSignInSucceeded();
}