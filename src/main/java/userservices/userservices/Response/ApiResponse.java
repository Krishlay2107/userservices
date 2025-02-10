package userservices.userservices.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.function.BinaryOperator;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private  String mesage;
    private boolean success;
     private HttpStatus status;
    private ApiResponse(Builder builder) {
        this.mesage = builder.message;
        this.success = builder.success;
    }

    // Static builder class
    public static class Builder {
        private String message;
        private boolean success;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }

    // Static method to get the builder instance
    public static Builder builder() {
        return new Builder();
    }
}




