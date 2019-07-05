import com.easyar.service.sts.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StsClient stsClient = new StsClient();
        /*
        *   Copy your APIKey and APISecret here
        */
        String apiKey = "YOUR APIKEY";
        String apiSecret = "YOUR APISECRET";

        final int expires = 3600; //seconds
        List<AccessControl> acl = null;

        /*
             ACL is optioanl
             ACL is used to control finer grain size and temporary permissions
         */
        final String resourceUuid = "Your spatial map space uuid";
        AccessControl ac = new AccessControl();
        ac.setService("ecs:spatialmap");
        ac.setEffect(Effect.Allow);
        ac.setResource(new String[]{resourceUuid});  //spatial map space UUID
        ac.setPermission(new Permission[]{Permission.READ});
        acl = new ArrayList<>();
        acl.add(ac);

        try {
            SecurityToken securityToken = stsClient.getSecurityToken(apiKey, apiSecret, expires, acl);
            System.out.println("security token: "+ securityToken.getToken());
            System.out.println("expiration date: "+ securityToken.getExpiration().toInstant().toString());
        } catch (Exception e) {
            System.out.println("exception: "+ e.getMessage());
        }

    }
}
