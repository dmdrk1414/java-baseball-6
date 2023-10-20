package baseball;

import baseball.domain.Judge;
import baseball.domain.NumbersGenerate;
import baseball.string.MyConstants;
import baseball.utill.Utill;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int INPUT_NUM_SIZE = 3;
    private static final int NOT_AGAIN_GAME = 2;
    private static final int WANT_AGAIN_GAME = 1;
    private static final int RESET_NUM = 0;
    static String resultStr; // 결과를 담는 문자열

    public static void main(String[] args) {
        List<Integer> computerList = new ArrayList<>(); // 컴퓨터의 3개의 랜덤 숫자 리스트
        List<Integer> userList = new ArrayList<>(); // 입력을 받는 userList
        Judge judge = new Judge(); // 게임을 판단해주는 판사님.

        while (true) {
            computerList = initGame();

            // resultStr 이 3스트라이크 아니면 반복을 한다.
            while (Utill.isNotSameString(resultStr, MyConstants.MSG_GAME_TERMINATION_CONDITION_STR())) {
                userList = inputRanNumOfUser(); // 1. 유저의 입력을 받아서 userList을 얻는다.
                resultStr = judge.baseballGameInspection(computerList, userList); // 2. judge의 힌트를 얻는다.
                System.out.println(resultStr); // 3. 힌트를 출력한다.
            }

            if (isNotProceedGame()) { // 4.게임의 종료 상황을 알려주고, 4.1 숫자 2을 누르면 게임 종료 진행
                break;
            }
        }
    }

    private static List<Integer> initGame() {
        NumbersGenerate numbersGenerate = new NumbersGenerate();
        resultStr = MyConstants.RESET_STRING(); // 결과 리셋
        return numbersGenerate.createRandomNumList(); // 컴퓨터의 3개의 랜덤 숫자 리스트
    }

    private static boolean isNotProceedGame() {
        int inputInt = RESET_NUM;

        System.out.println(MyConstants.MSG_END_GAME_COMMENT_STR()); // 1. "3개의 숫자를 모두 맞히셨습니다! 게임 종료"
        System.out.println(MyConstants.MSG_GAME_PROGRESS_SELECTION_STATUS_STR()); // 2. "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."

        // 3. 숫자를 입력받는다.
        inputInt = Utill.inputIntNum();

        // 4. 입력 받는 숫자가 1이면 true
        if (Utill.isSameInteger(inputInt, WANT_AGAIN_GAME)) {
            return false;
        } else if (Utill.isSameInteger(inputInt, NOT_AGAIN_GAME)) {
            return true;
        } else { // TODO: 10/20/23 예외! 1과 2을 제외한 숫자는 예외
            throw new IllegalArgumentException("1과 2만 입력해주세요.");
        }
    }

    /**
     * 문자열 입력을 받아 List<Integer>의 LIST을 반환한다.
     *
     * @return "123" => {1 , 2, 3}
     */

    private static List<Integer> inputRanNumOfUser() {
        List<Integer> inputList = new ArrayList<>();
        String inputStrArr[] = new String[INPUT_NUM_SIZE];

        System.out.print(MyConstants.MSG_INPUT_NUMBER_STR()); // 1. 입력을 받기 전 출력 문구 "숫자를 입력해주세요 : "
        String numInputOfUser = Utill.inputNum(); // 2. 숫자 입력을 받는다.

        // 3. 입력을 받은 문자렬을 split을 한다음 inputList에 추가를 한다. => [ 1, 2, 3]
        inputStrArr = numInputOfUser.split("");

        for (String s : inputStrArr) {
            int numParseInt = Integer.valueOf(s);
            inputList.add(numParseInt);
        }

        return inputList;
    }
}
