package baseball.utill;

import baseball.string.MyConstants;
import baseball.string.ValidConstants;

public class ValidException {
    public final static int LENGTH_ALLOW_TREE = 3;
    private static final int NOT_AGAIN_GAME = 2;
    private static final int WANT_AGAIN_GAME = 1;

    /**
     * 문자열의 길이가 3이 아니면 예외 처리
     *
     * @param str
     */
    public static void isValidLength(String str) {
        if (str.length() == LENGTH_ALLOW_TREE) {
            return;
        }
        throw new IllegalArgumentException(ValidConstants.MSG_INPUT_LENGTH_ONLY_THREE());
    }


    /**
     * 유저가 3자릿수의 랜덤 숫자을 입력할때 1과 2가 아니면 예외 발생을 한다.
     *
     * @param num
     */
    public static void isValidOnlyInputOneTwo(int num) {
        if (Utill.isSameInteger(num, WANT_AGAIN_GAME) || Utill.isSameInteger(num, NOT_AGAIN_GAME)) {
            return;
        }
        throw new IllegalArgumentException(ValidConstants.MSG_INPUT_LENGTH_ONLY_THREE());
    }
}
