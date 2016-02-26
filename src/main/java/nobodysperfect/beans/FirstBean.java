package nobodysperfect.beans;

import org.springframework.stereotype.Component;


@Component
public class FirstBean {

    private final SecondBean secondBean;

    public FirstBean(SecondBean secondBean) {
        this.secondBean = secondBean;
    }

    public String getName() {
        return secondBean.getName();
    }

}
