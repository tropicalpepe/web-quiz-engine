package engine.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SolutionRequest {
    private List<Integer> answer = new ArrayList<>();
}
