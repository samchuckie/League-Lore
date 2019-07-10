package com.example.samuelnyamai.leagurelore.Constants;

import com.example.samuelnyamai.leagurelore.BuildConfig;

public class ServerConstants {
        public static final String HTTP = "https://";
        public static final String NA_BASE_URL = "na1";
        public static final String JPN_BASE_URL ="jp1" ;
        public static final String OCE_BASE_URL = "oc1";
        public static final String BZL_BASE_URL = "br1";
        public static final String EUW_BASE_URL= "euw1";

        public static final String LOCALHOST = "http://192.168.137.1:8080/";
        public static final String LOCAL_URL_PROFILE = "http://192.168.137.1:8080/img/profileicon/";
        public static final String PROFILE_BASE_URL ="https://ddragon.leagueoflegends.com/cdn/9.6.1/img/profileicon/";

        public static final String LOCAL_URL_LOADING = "http://192.168.137.1:8080/img/champion/loading/";
        public static final String CHAMPION_LOADINGSMALLIMAGE_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/";

        public static final String LOCAL_URL_IMAGESPLASH = "http://192.168.137.1:8080/img/champion/splash/";
        public static final String CHAMPION_LOADINGIMAGE_URL = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";

        public static final String LOCAL_URL_PASSIVE = "http://192.168.137.1:8080/img/passive/";
        public static final String CHAMPION_PASSIVE = "http://ddragon.leagueoflegends.com/cdn/9.6.1/img/passive/";

        public static final String LOCAL_URL_SPELL = "http://192.168.137.1:8080/img/spell/";
        public static final String CHAMPION_SPELL = "http://ddragon.leagueoflegends.com/cdn/9.6.1/img/spell/";

        public static final String LOCAL_URL_CHAMPION = "http://192.168.137.1:8080/img/champion/";
        public static final String CHAMPION_ICON_BASE_URL = "http://ddragon.leagueoflegends.com/cdn/9.6.1/img/champion/";

        public static final String BY_NAME =".api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        public static final String CHAMPIONS_MASTERY_BASE_URL = ".api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";

        public static final String RANKED_BASE_URL = ".api.riotgames.com/lol/league/v4/entries/by-summoner/";
        public static final String CHAMPIONS_BASE_URL = "http://ddragon.leagueoflegends.com/cdn/9.6.1/data/en_US/";

        public static final String PNG_IMAGE_EXTENSION = ".png";
        public static final String JPG_IMAGE_EXTENSION = ".jpg";
        public static final String DATABASE_NAME ="Champions";

        public static final String KEY_EXTRA = "KEY";
        public static final String NAME_EXTRA = "NAME";
        public static final String USERNAME_EXTRA = "username_extra";
        public static final String SERVER_EXTRA = "server_extra";
        public static final String TITLE_EXTRA = "TITLE";
        public static final String ID_EXTRA = "ID";
        public static final String API_KEY = BuildConfig.ApiKey;
        public static final String IMAGE_EXTRA = "IMAGE";

        public final static String REGEX = "[a-zA-Z0-9]+@[A-z]+(.[A-z])+";
        public final static String NETWORK = "A network error (such as timeout, interrupted connection or unreachable host) has occurred.";
        public static String UNREGISTERED = "There is no user record corresponding to this identifier. The user may have been deleted.";
}
