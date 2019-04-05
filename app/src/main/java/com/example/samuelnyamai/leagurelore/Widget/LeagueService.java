package com.example.samuelnyamai.leagurelore.Widget;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;


// TODO ADD SERVICE


class LeagueService  extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new LeagueViewService(this.getApplicationContext(), intent);

    }

    private class LeagueViewService implements RemoteViewsFactory {
        LeagueViewService(Context applicationContext, Intent intent) {

        }

        @Override
        public void onCreate() {
            List<String> positions = new ArrayList<>();
            positions.add("ss");
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}