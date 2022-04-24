package cn.lz.spring.constants;

public interface Food {

    String description();

    enum HumanFood implements Food {
        POTATO {
            @Override
            public String description() {
                return "我觉得很好吃";
            }
        }
    }

    enum AnimalFood implements Food {
        BONE {
            @Override
            public String description() {
                return "狗狗觉得很好吃";
            }
        }
    }

    static void main(String[] args) {
        System.out.println(HumanFood.POTATO.description());
    }
}
