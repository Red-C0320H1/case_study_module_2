package com.red.app.zingmp3;

public interface ZingInfo {
	String API_URL              = "https://m.zingmp3.vn/api";
	String URL_DETAIL           = "/chart-realtime/get-detail";
	String URL_SOUNG_INFO       = "/song/get-song-info";        // param: ID
	String URL_LYRIC            = "/song/get-lyric";            // param: ID
	String URL_STREAMMING       = "/song/get-streamings";       // param: ID
	String URL_STREAMMING_BEAT  = "/song/get-streamings-beat";  // param: ID
	String URL_PLAYLIST         = "/playlist/get-playlist-detail";
	String URL_SEARCH           = "/search";
	String URL_SEARCH_MULTI     = "/search/multi";
	String URL_SEARCH_HOT_KEY   = "/search/get-hot-key";
	String URL_GET_PROFILE      = "/user/get-profile";
	String URL_GET_VIDEO_DETAIL = "/video/get-video-detail";  // param: ID


	String REGEX_URL   = "(https?:)?\\/\\/(.*)";
	String HMAC_SHA512 = "HmacSHA512";
	String SHA256      = "SHA-256";
	String API_KEY     = "38e8643fb0dc04e8d65b99994d3dafff";
	String PRIVATE_KEY = "10a01dcf33762d3a204cb96429918ff6";
}
