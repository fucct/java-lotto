package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.InvalidNumberException;

public class NumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "45", "33"})
    @DisplayName("정상적으로 로또를 구성하는 수를 생성하는 경우")
    void correctNumber(String value) {
        Assertions.assertThat(new Number(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "-1"})
    @DisplayName("정상적인 수의 범위를 벗어나는 경우")
    void rangeOver(String value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(InvalidNumberException.class)
                .hasMessageMatching("로또 번호는 1에서 45까지만 가능합니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "100a", "a100", "천원"})
    @DisplayName("수를 문자열로 입력하는 경우")
    void numberFormatTest(String value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(InvalidNumberException.class)
                .hasMessageMatching("문자는 사용이 불가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void 공백_테스트(String value) {
        assertThatThrownBy(() -> new Number(value))
                .isInstanceOf(InvalidNumberException.class)
                .hasMessageMatching("공백은 사용이 불가능합니다.");
    }

    @Test
    void NULL_테스트() {
        assertThatThrownBy(() -> new Number(null))
                .isInstanceOf(InvalidNumberException.class)
                .hasMessageMatching("Null문자열은 사용이 불가능합니다.");
    }
}
