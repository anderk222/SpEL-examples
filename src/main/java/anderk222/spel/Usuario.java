package anderk222.spel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private static String NO_VALUE = "N/A";

    @Default
    private String name = NO_VALUE;
    @Default
    private int edad = 0;

}
