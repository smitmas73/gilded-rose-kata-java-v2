package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class Item {
    private String name;
    private Integer sellIn;
    private Integer quality;

    public void updateItemQuality() {
    }
}
