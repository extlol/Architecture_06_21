package arch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestResponse {
    private final boolean isSuccessful;
    private String error;

    public RequestResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}
