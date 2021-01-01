package com.moeid.framework.mvp.ui.Activity_Splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.moeid.framework.mvp.R;
import com.moeid.framework.mvp.ui.Activity_Login.Login;
import com.moeid.framework.mvp.ui.base.BaseActivity;
import com.rbddevs.splashy.Splashy;

import java.net.URISyntaxException;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author MoeidTopcoder
 * @version 1.0
 */

public class Splash extends BaseActivity implements SplashMvpView {
    /**
     * @author MoeidTopcoder
     * inject a presenter of this activity for business logic purpose
     */
    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;
    /**
     * @param context
     * @return static Intent
     * @author MoeidTopcoder
     * get an instance of this activity by this static function when ever creating an intent with this activity will be needed
     */
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, Splash.class);
        return intent;
    }
    /**
     * everything in this activity start here as an entry point
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(Splash.this);

        try {
            setUp();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * @throws URISyntaxException
     * @author MoeidTopcoder
     * entry point of this activity after onCreate function
     */
    @Override
    protected void setUp() throws URISyntaxException {

        // Initialize the splash onject to be represented
        new Splashy(this)
                .setBackgroundColor("#B50540")
                .setTitle("سنه آگرین")
                .setTitleColor("#FFFFFF")
                .setSubTitle("اپلیکیشن جامع مکان یابی")
                .setSubTitleColor("#FFFFFF")
                .setFullScreen(true)
                .setAnimation(Splashy.Animation.SLIDE_IN_TOP_BOTTOM, 200)
                .setLogo(R.drawable.snaagrin_icon)
                .setTime(5000)
                .show();


        //start the new activity after the splash ends with this listener
        Splashy.Companion.onComplete(new Splashy.OnComplete() {
            @Override
            public void onComplete() {
                startLoginPage();
            }
        });


    }

    /**
     * @author MoeidTopcoder
     * to start the login activity after the duration of splash
     */
    public void startLoginPage() {
        startActivity(Login.getStartIntent(Splash.this));


    }
}