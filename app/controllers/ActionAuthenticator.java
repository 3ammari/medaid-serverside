package controllers;

import models.User;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by abdul-rahman on 17/05/16.
 */
public class ActionAuthenticator extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context context) {
        String token = getTokenFromHeader(context);
        if (token != null) {
            User user = User.find.where().eq("authToken", token).findUnique();
            if (user != null) {
                return user.getEmail();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}
