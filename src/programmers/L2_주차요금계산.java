import java.util.*;
class Solution {
    static class Car {
        int id, hour, minutes;

        public Car(int id, int hour, int minutes) {
            this.id = id;
            this.hour = hour;
            this.minutes = minutes;
        }
    }
    static int dMinutes, dFees, uMinutes, uFees;
    static int SIZE;
    static  String IN="IN", OUT="OUT";
    public int[] solution(int[] fees, String[] records) {
        dMinutes = fees[0];
        dFees = fees[1];
        uMinutes = fees[2];
        uFees = fees[3];

        SIZE = records.length;

        Map<Integer, Car> inList = new HashMap<>();
        Map<Integer, Integer> timeList = new HashMap<>();

        StringTokenizer token;
        int hour, minutes, id;
        String type;

        for (String r:records) {
            token = new StringTokenizer(r, ": ");
            hour = Integer.parseInt(token.nextToken());
            minutes = Integer.parseInt(token.nextToken());
            id = Integer.parseInt(token.nextToken());
            type = token.nextToken();

            if(IN.equals(type)) inList.put(id, new Car(id, hour, minutes));
            else {
                int time = 0;
                if(timeList.containsKey(id)) time += timeList.get(id);
                time += addTime(inList.get(id), hour, minutes);
                inList.remove(id);
                timeList.put(id, time);
            }
        }
        // 출차기록 없으면 23:59출차로 처리
        for (Car car:inList.values()) {
            int time = 0;
            if(timeList.containsKey(car.id)) time += timeList.get(car.id);
            time += addTime(inList.get(car.id), 23, 59);
            timeList.put(car.id, time);
        }

        // 차량번호 오름차순으로
        ArrayList<Car> idList = new ArrayList<>();
        ArrayList<Integer> sortList = new ArrayList<>();
        for (int key:timeList.keySet()) sortList.add(key);

        Collections.sort(sortList);
        int size = sortList.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = getFee(timeList.get(sortList.get(i)));
        }

        return res;
    }

    private int getFee(int minutes) {
        int fee = 0;

        // 기본요금
        if(minutes<=dMinutes) return dFees;

        fee += dFees;
        minutes -= dMinutes;

        // 추가요금(올림 연산)
        double unit = Math.ceil((double)minutes/uMinutes);
        fee += uFees * unit;

        return fee;
    }

    private int addTime(Car car, int fh, int fm) {
        int sh = car.hour;
        int sm = car.minutes;

        sm += sh*60;
        fm += fh*60;

        return fm-sm;
    }
}
