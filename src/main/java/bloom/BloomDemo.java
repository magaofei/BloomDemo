package bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

public class BloomDemo {

    private static int size = 10000;
    public static void main(String[] args) {

        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.01);

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        for (int i = 0; i < size; i++) {
            if (bloomFilter.mightContain(i)) {
                System.out.println("有坏人逃脱了");
            }
        }

        List<Integer> list = new ArrayList<>(1000);
        for (int i = size + 1000; i < size + 2000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }

        System.out.println("有误伤的数量 " + list.size());
    }
}
