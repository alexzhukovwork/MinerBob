package com.mygdx.minerbob;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.mygdx.minerbob.gameworld.GameWorld;
import com.mygdx.minerbob.helpers.AssetLoader;
import com.mygdx.minerbob.helpers.Money;
import com.mygdx.minerbob.helpers.MyDate;

import java.util.Date;

import static android.provider.UserDictionary.Words.APP_ID;


public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler, IRewardVideo {

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	private RewardedVideoAd mAd;

	protected AdView adView;
	protected View gameView;
    private GameWorld gameWorld;

	public BitmapFont generateFont() {
		final String FONT_PATH = "arial.ttf";
		BitmapFont font;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ProximaNova-Semibold.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters =
				"абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyz" +
						"АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789]" +
						"[_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.borderWidth = 2;
		parameter.borderColor = Color.BLACK;
		parameter.size = 78;

		parameter.flip = true;
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
        font.getData().setScale(0.1f, 0.1f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		generator.dispose();
		return font;
	}

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case SHOW_ADS:
					adView.setVisibility(View.VISIBLE);
					break;
				case HIDE_ADS:
					adView.setVisibility(View.GONE);
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		RelativeLayout layout = new RelativeLayout(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		View gameView = initializeForView(new MainGame(this), config);

		layout.addView(gameView);

		adView = new AdView(this);

		// В файле strings.xml создайте строку с вашим publisher_id

		adView.setAdUnitId(getResources().getString(R.string.publisher_id));

		// Устанавливаем размер объявления

		adView.setAdSize(AdSize.SMART_BANNER);

		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // Указываем тестовый режим на эмуляторе
				.addTestDevice("04DBBFBA3AE8F069512B7E9B2200B932") // ID устройства. Его видно в логе после первого запуска
				.addTestDevice("D339B6E991AEC0AA2CAC570AFBF35887")
				.build();

		// Закружаем объявления

		adView.loadAd(adRequest);

		RelativeLayout.LayoutParams adParams =
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		layout.addView(adView, adParams);

		setContentView(layout);

		MobileAds.initialize(this, APP_ID);

		mAd = MobileAds.getRewardedVideoAdInstance(this);
		mAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
			@Override
			public void onRewardedVideoAdLoaded() {

			}

			@Override
			public void onRewardedVideoAdOpened() {

			}

			@Override
			public void onRewardedVideoStarted() {

			}

			@Override
			public void onRewardedVideoAdClosed() {
				loadRewardedVideoAd();
			}

			@Override
			public void onRewarded(RewardItem rewardItem) {

				if(gameWorld.isAdShop()) {
					//Log.i("InfoTag", "EEE boy. Shop AD");
					AssetLoader.prefs.putInteger("countVideo", AssetLoader.prefs.getInteger("countVideo") + 1);
					AssetLoader.prefs.flush();
					if (AssetLoader.prefs.getInteger("countVideo") == 1)
						Money.add(20);
					else
						Money.add(10);
				}
				else
					if(gameWorld.isAdRestore()) {
                        gameWorld.restore();
                        AssetLoader.prefs.putInteger("countRestore", AssetLoader.prefs.getInteger("countRestore") + 1);
                        AssetLoader.prefs.flush();
					}

				loadRewardedVideoAd();  // Load for next Reward Point

			}

			@Override
			public void onRewardedVideoAdLeftApplication() {

			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int i) {

			}
		});
		loadRewardedVideoAd();
	}

	private void loadRewardedVideoAd() {
		mAd.loadAd("ca-app-pub-2837758853531177/7389204663", new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("04DBBFBA3AE8F069512B7E9B2200B932")
				.addTestDevice("D339B6E991AEC0AA2CAC570AFBF35887")
				.build());
	}

	public void showVideoAd(){
		runOnUiThread(new Runnable() {
			public void run() {

				if (mAd.isLoaded()) {
					mAd.show();
				} else {
					loadRewardedVideoAd();
				}
			}
		});
	}

    @Override
    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public boolean hasVideoReward(){
		return mAd.isLoaded();
	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("Resume Activity");
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("Pause Activity");
		if (adView != null) {
			adView.pause();
		}
	}

	@Override
	public void onDestroy() {
		System.out.println("Destroy Activity");
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
