package com.google.android.gms.internal;

import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.search.SearchAuth;

public final class zzffj {
    public static int zzkz(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                break;
            default:
                switch (i) {
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                        break;
                    default:
                        switch (i) {
                            case SearchAuth.StatusCodes.AUTH_DISABLED /*10000*/:
                            case 10001:
                            case GamesActivityResultCodes.RESULT_SIGN_IN_FAILED:
                            case GamesActivityResultCodes.RESULT_LICENSE_FAILED:
                            case GamesActivityResultCodes.RESULT_APP_MISCONFIGURED:
                            case GamesActivityResultCodes.RESULT_LEFT_ROOM:
                            case GamesActivityResultCodes.RESULT_NETWORK_FAILURE:
                            case GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED:
                            case GamesActivityResultCodes.RESULT_INVALID_ROOM:
                            case 10009:
                                break;
                            default:
                                switch (i) {
                                    case 99999:
                                    case 100000:
                                        break;
                                    default:
                                        StringBuilder sb = new StringBuilder(43);
                                        sb.append(i);
                                        sb.append(" is not a valid enum ContextName");
                                        throw new IllegalArgumentException(sb.toString());
                                }
                        }
                }
        }
        return i;
    }
}
