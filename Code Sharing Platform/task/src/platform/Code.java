package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "code_snippets")
public class Code {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String uuid = CodeService.getUUID();

    private String code = "public static void main(String[] args) {\\n    SpringApplication.run(CodeSharingPlatform.class, args);\\n}";

    private String date = CodeService.formatDate();

    private long time = -1;

    private int views = -1;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean hasViewLimit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean hasTimeLimit;
}
