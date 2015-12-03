package networking.requests;

import networking.DataMessage;

import java.io.IOException;
import java.util.ArrayList;

public class SignInTokenRequest {


    ServerRequest serverRequest;
    String token;
    String gcmId;

    public SignInTokenRequest(ServerRequest request) {
        serverRequest = request;


        ArrayList<byte[]> blocks = DataMessage.getBlocks(serverRequest.getRequestBytes());
        token = new String (blocks.get(0));
        gcmId = new String (blocks.get(1));


    }

    public String getToken() {
        return token;
    }
    public String getGcmId() {
        return gcmId;
    }

    public static byte[] createMessage(String token, String gcmId) throws IOException {

        ArrayList<byte[]> signInBlocks = new ArrayList<>();

        signInBlocks.add(token.getBytes());
        signInBlocks.add(gcmId.getBytes());

        return ServerRequest.createMessage(DataMessage.createBlocks(signInBlocks), ServerRequest.ServerRequestType.SignInToken);
    }

}
