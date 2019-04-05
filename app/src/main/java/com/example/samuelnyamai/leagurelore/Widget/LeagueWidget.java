package com.example.samuelnyamai.leagurelore.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import com.example.samuelnyamai.leagurelore.LogIn;
import com.example.samuelnyamai.leagurelore.R;
import com.example.samuelnyamai.leagurelore.SummonerActivity;
import static android.content.Context.MODE_PRIVATE;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.SERVER_EXTRA;
import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.USERNAME_EXTRA;

public class LeagueWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // TODO IMPLIMENT IF SHARED PREF NULL
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.league_widget);
        Intent intent;
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), MODE_PRIVATE);
        if (sharedPreferences.contains(context.getString(R.string.summoner_name_key))) {
            String username_pref,server_pref;
            username_pref = sharedPreferences.getString(context.getString(R.string.summoner_name_key), null);
            server_pref = sharedPreferences.getString(context.getString(R.string.summoner_server), null);
            intent = new Intent(context , SummonerActivity.class);
            intent.putExtra(USERNAME_EXTRA ,username_pref);
            intent.putExtra(SERVER_EXTRA ,server_pref);
        }
        else{
            intent = new Intent(context , LogIn.class);

        }

            PendingIntent clickedPendingIntent = PendingIntent.getActivity(context,0 ,intent,0);
            views.setOnClickPendingIntent(R.id.league_icon, clickedPendingIntent);


        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

