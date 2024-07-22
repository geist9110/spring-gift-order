package gift.option;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/{productId}/option")
    public List<OptionDTO> getOption(@PathVariable("productId") long productId) {
        return optionService.getOptions(productId);
    }

    @PostMapping("/{productId}/option")
    public void addOption(
        @PathVariable("productId") long productId,
        @Valid @RequestBody OptionDTO optionDTO
    ) {
        optionService.addOption(productId, optionDTO);
    }

    @PatchMapping("/{productId}/option")
    public void updateOption(
        @PathVariable("productId") long productId,
        @Valid @RequestBody OptionDTO optionDTO
    ) {
        optionService.updateOption(productId, optionDTO);
    }

    @DeleteMapping("/{productId}/option/{optionId}")
    public void deleteOption(
        @PathVariable("productId") long productId,
        @PathVariable("optionId") long optionId
    ) {
        optionService.deleteOption(productId, optionId);
    }
}
