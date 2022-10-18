import com.google.common.collect.ImmutableList;
import data.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GildedRoseTest {

    @InjectMocks
    private GildedRose gildedRose;

    @ParameterizedTest
    @MethodSource("updateQualitySpecialItemNameScenarios")
    void updateQuality_shouldUpdateSpecial_givenSpecialItem(String name) {
        Integer sellIn = 10;
        Integer quality = 10;

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
    }

    static Stream<Arguments> updateQualitySpecialItemNameScenarios() {
        return Stream.of(
                Arguments.of("Aged Brie"),
                Arguments.of("Backstage passes"),
                Arguments.of("Sulfuras"),
                Arguments.of("Conjured")
        );
    }

    @ParameterizedTest
    @MethodSource("updateQualityDefaultItemScenarios")
    void updateQuality_shouldUpdateDefault_givenNonSpecialItem(Integer sellIn,
                                                               Integer quality,
                                                               Integer expectedSellIn,
                                                               Integer expectedQuality) {
        String name = "foo";

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(expectedSellIn);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(expectedQuality);
    }

    static Stream<Arguments> updateQualityDefaultItemScenarios() {
        return Stream.of(
                Arguments.of(10, 10, 9, 9),
                Arguments.of(1, 10, 0, 9),
                Arguments.of(0, 10, -1, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("updateQualitySpecialItemScenarios")
    void updateQuality_shouldUpdateSpecialItem_givenSpecialItems(String name,
                                                                 Integer expectedSellIn,
                                                                 Integer expectedQuality) {
        Integer sellIn = 10;
        Integer quality = 10;

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(expectedSellIn);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(expectedQuality);
    }

    static Stream<Arguments> updateQualitySpecialItemScenarios() {
        return Stream.of(
                Arguments.of("Aged Brie", 9, 11),
                Arguments.of("Backstage passes", 9, 12),
                Arguments.of("Sulfuras", 10, 10),
                Arguments.of("Conjured", 9, 8)
        );
    }

    @Test
    void updateQuality_shouldUpdateDefaultItem_givenNonSpecialItem() {
        String name = "foo";
        Integer sellIn = 10;
        Integer quality = 10;

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(9);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(9);
    }

    @ParameterizedTest
    @MethodSource("backstagePassesValueUpdateScenarios")
    void updateQuality_shouldUpdateBackstagePasses_givenBackstagePassItem(Integer sellIn,
                                                                          Integer quality,
                                                                          Integer expectedSellIn,
                                                                          Integer expectedQuality) {
        String name = "Backstage passes";

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(expectedSellIn);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(expectedQuality);
    }

    static Stream<Arguments> backstagePassesValueUpdateScenarios() {
        return Stream.of(
                Arguments.of(10, 10, 9, 12),
                Arguments.of(5, 10, 4, 13),
                Arguments.of(1, 10, 0, 13),
                Arguments.of(0, 10, -1, 0),
                Arguments.of(10, 50, 9, 50),
                Arguments.of(5, 50, 4, 50)
        );
    }

    @ParameterizedTest
    @MethodSource("cheeseValueUpdateScenarios")
    void updateQuality_shouldUpdateBrieCheese_givenBrieCheeseItem(Integer sellIn,
                                                                  Integer quality,
                                                                  Integer expectedSellIn,
                                                                  Integer expectedQuality) {
        String name = "Aged Brie";

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(expectedSellIn);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(expectedQuality);
    }

    static Stream<Arguments> cheeseValueUpdateScenarios() {
        return Stream.of(
                Arguments.of(0, 10, -1, 12),
                Arguments.of(0, 50, -1, 50)
        );
    }

    @ParameterizedTest
    @MethodSource("conjuredValueUpdateScenarios")
    void updateQuality_shouldUpdateConjured_givenConjuredItem(Integer sellIn,
                                                              Integer quality,
                                                              Integer expectedSellIn,
                                                              Integer expectedQuality) {
        String name = "Conjured";

        gildedRose = new GildedRose(ImmutableList.of(new Item(name, sellIn, quality)));

        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()).hasSize(1);
        assertThat(gildedRose.getItems().get(0).getName()).isEqualTo(name);
        assertThat(gildedRose.getItems().get(0).getSellIn()).isEqualTo(expectedSellIn);
        assertThat(gildedRose.getItems().get(0).getQuality()).isEqualTo(expectedQuality);
    }

    static Stream<Arguments> conjuredValueUpdateScenarios() {
        return Stream.of(
                Arguments.of(10, 10, 9, 8),
                Arguments.of(0, 10, -1, 6),
                Arguments.of(0, 3, -1, 0)
        );
    }
}