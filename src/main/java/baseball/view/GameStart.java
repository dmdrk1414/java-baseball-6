package baseball.view;

import baseball.control.GameProgress;
import baseball.domain.Judge;
import baseball.domain.NumbersGenerate;
import baseball.string.MyConstants;
import baseball.utill.Utill;
import baseball.utill.ValidException;

import java.util.ArrayList;
import java.util.List;

public class GameStart {
    private static final int INPUT_NUM_SIZE = 3;
    private static final int NOT_AGAIN_GAME = 2;
    private static final int RESET_NUM = 0;
    private String hintOfJudge; // 결과를 담는 문자열
    private List<Integer> computerList; // 컴퓨터의 3개의 랜덤 숫자 리스트
    private List<Integer> userList; // 입력을 받는 userList
    private GameProgress gameProgress; // 게임 진행 클래스
    private NumbersGenerate numbersGenerate; // 게임 숫자 설정 객체.


    public GameStart() {
        gameProgress = new GameProgress();
        computerList = new ArrayList<>();
        userList = new ArrayList<>();
        numbersGenerate = new NumbersGenerate();
    }

    public void run() {

        while (true) {
            initGame(numbersGenerate);

            // resultStr 이 "3스트라이크" 아니면 반복을 한다.
            while (Utill.isNotSameString(hintOfJudge, MyConstants.MSG_GAME_TERMINATION_CONDITION_STR())) {
                inputRanNumOfUser(); // 1. 유저의 입력을 받아서 userList을 얻는다.
                hintOfJudge = gameProgress.getHintOfJudge(computerList, userList); // 2. judge의 힌트를 얻는다.
                System.out.println(hintOfJudge); // 3. 힌트를 출력한다.
            }

            if (isNotProceedGame()) { // 4.게임의 종료 상황을 알려주고, 4.1 숫자 2을 누르면 게임 종료 진행
                break;
            }
        }
    }


    /**
     * 게임을 초기화 할때 사용을 한다. 힌트의 값과, 컴퓨터의 랜덤 리스트를 초기화한다..
     *
     * @return
     */
    private void initGame(NumbersGenerate numbersGenerate) {
        hintOfJudge = MyConstants.RESET_STRING(); // 결과 리셋
        computerList = numbersGenerate.createRandomNumList(); // 컴퓨터의 3개의 랜덤 숫자 리스트
    }

    /**
     * 3스트라이크가 되면 게임 진행을 위한 절차를 호출하는 메서드.
     *
     * @return
     */
    private boolean isNotProceedGame() {
        int inputInt = RESET_NUM;

        System.out.println(MyConstants.MSG_END_GAME_COMMENT_STR()); // 1. "3개의 숫자를 모두 맞히셨습니다! 게임 종료"
        System.out.println(MyConstants.MSG_GAME_PROGRESS_SELECTION_STATUS_STR()); // 2. "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."


        inputInt = Utill.inputIntNum(); // 3. 숫자를 입력받는다.

        ValidException.isValidOnlyInputOneTwo(inputInt); // 1과 2가 아니면 예외처리

        if (Utill.isSameInteger(inputInt, NOT_AGAIN_GAME)) {  // 4. 입력 받는 숫자가 2이면 true - STOP
            return true;
        }

        // 5. 입력 받는 숫자가 1이면 - start
        return false;
    }

    /**
     * 문자열 입력을 받아 List<Integer>의 LIST을 반환한다.
     *
     * @return "123" => {1 , 2, 3}
     */

    private void inputRanNumOfUser() {
        initUserList();

        System.out.print(MyConstants.MSG_INPUT_NUMBER_STR()); // 1. 입력을 받기 전 출력 문구 "숫자를 입력해주세요 : "
        String numInputOfUser = Utill.inputNum(); // 2. 숫자 입력을 받는다.

        // 3. 입력을 받은 문자렬을 split을 한다음 inputList에 추가를 한다. => [ 1, 2, 3]
        addNumber2InputList(userList, numInputOfUser);
    }

    /**
     * userList을 초기화 해주는 메서드
     */
    private void initUserList() {
        userList = new ArrayList<>();
    }

    /**
     * 입력 받은 문자열 "123"을 InputList에 1, 2, 3 을 추가한다.
     *
     * @param inputList      입력받는 문자열을 추가한 리스트 {1, 2, 3}
     * @param numInputOfUser 입력받는 문자열 "123"
     */
    private void addNumber2InputList(List<Integer> inputList, String numInputOfUser) {
        String[] inputStrArr;

        inputStrArr = Utill.getSplitStrArr(numInputOfUser);
        Utill.makeListFromArr(inputList, inputStrArr);
    }
}
