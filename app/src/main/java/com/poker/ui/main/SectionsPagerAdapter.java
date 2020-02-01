package com.poker.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.poker.CashGame;
import com.poker.R;
import com.poker.TorneoEspecial;
import com.poker.TorneoSimple;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
                case 0:
                    CashGame cashGame = new CashGame();
                    return cashGame;

                case 1:
                    TorneoSimple TorneoSimple = new TorneoSimple();
                    return TorneoSimple;

                case 2:
                    TorneoEspecial TorneoEspecial = new TorneoEspecial();
                    return TorneoEspecial;
                default:
                    return null;

            }
        }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
           switch (position) {
                case 0:
                    return "Cash Game";
                case 1:
                    return "Torneo Simple";
                case 2:
                    return "Torneo Especial";
            }
            return null;
        }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}