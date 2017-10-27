package com.mygdx.minerbob;

import com.mygdx.minerbob.gameworld.GameWorld;

/**
 * Created by Алексей on 30.09.2017.
 */

public interface IRewardVideo {
    public boolean hasVideoReward();
    public void showVideoAd();
    public void setGameWorld(GameWorld gameWorld);
}
