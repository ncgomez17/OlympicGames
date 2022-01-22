package com.juegosolimpicos.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.common.base.Objects;
import org.springframework.lang.Nullable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ValueDifferenceDTO<V> {

    private final V left;

    private final V right;

    public static <V> ValueDifferenceDTO build(@Nullable V left, @Nullable V right) {
        return new ValueDifferenceDTO<V>(left, right);
    }

    private ValueDifferenceDTO(@Nullable V left, @Nullable V right) {
        this.left = left;
        this.right = right;
    }

    public V leftValue() {
        return left;
    }

    public V rightValue() {
        return right;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object instanceof ValueDifferenceDTO) {
            ValueDifferenceDTO<?> that = (ValueDifferenceDTO<?>) object;
            return Objects.equal(this.left, that.leftValue())
                    && Objects.equal(this.right, that.rightValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(left, right);
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }

}
