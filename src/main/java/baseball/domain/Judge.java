package baseball.domain;

import baseball.utill.Utill;

import java.util.List;

public class Judge {
    private static final Integer NOT_THING_RESULT = 0;
    GameStandard gameStandard;

    public Judge() {
        gameStandard = new GameStandard();
    }

    /**
     * computerList와 userList을
     *
     * @param computerList
     * @param userList
     * @return
     */
    public String baseballGameInspection(List<Integer> computerList, List<Integer> userList) {
        int cntTotalSameUserAndComputer = 0; // computerList 중에 userList와 같은 것이 몇개인가.
        int cntStrike = 0; // strike의 갯수
        int cntBall = 0; // ball의 갯수
        boolean isSamePlaceIndexUserAndCom = true; // 유저의 특정 index와 컴퓨터의 특정 index의 자리의 숫자가 같은가.
        int numIndexOfUser = 0; // UserList의 특정 index의 값

        // 1. computerList 중에 userList와 같은 것이 몇개인지 구한다. - 숫자가 같은 갯수
        cntTotalSameUserAndComputer = gameStandard.getSameNumber2Computer(computerList, userList);

        // 2. userList의 각각의 숫자와 computerList 중에 같은 것이 몇개인지 구한다 - 스트라이크 갯수
        int sizeUserList = userList.size();
        for (int indexUserList = 0; indexUserList < sizeUserList; indexUserList++) {
            numIndexOfUser = userList.get(indexUserList); // index의 UserList의 값
            isSamePlaceIndexUserAndCom = gameStandard.isSamePlaceNumUserAndComputer(computerList, indexUserList, numIndexOfUser); // user의 특정 index의 값과 computer의 특정 index의 값이 같은가.

            if (isSamePlaceIndexUserAndCom) { // user의 특정 index의 값과 computer의 특정 index의 값이 같으면.
                cntStrike = cntStrike + 1; // 스트라이크의 갯수 증가.
            }
        }

        // 3. 숫자가 같은 갯수 - 스트라이크 갯수 는 볼의 갯수이다. - 볼의 갯수
        cntBall = cntTotalSameUserAndComputer - cntStrike;

        // 4. 숫자가 같은 갯수가 없다면 "낫싱"
        if (Utill.isSameInteger(cntTotalSameUserAndComputer, NOT_THING_RESULT)) {
            return "낫싱";
        }

        // 5. 볼이 없다면 "3스트라이크"
        if (Utill.isSameInteger(cntBall, NOT_THING_RESULT)) {
            return cntStrike + "스트라이크";
        }

        // 6. 볼만 있다면
        if (Utill.isSameInteger(cntStrike, NOT_THING_RESULT)) {
            return cntBall + "볼";
        }

        // 7. 볼과 스트라이크이 있다면 "1볼 1스트라이크"
        return cntBall + "볼 " + cntStrike + "스트라이크";
    }
}
