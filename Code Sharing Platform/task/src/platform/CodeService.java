package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CodeService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private CodeRepository codeRepository;

    public static String formatDate() {
        LocalDateTime currentDT = LocalDateTime.now();
        return currentDT.format(formatter);
    }

    public Code getSnippet(String uuid) {
        Code code = codeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        System.out.println("CODE " + code.getCode() + " " + code.getDate());
        if (code.isHasViewLimit()) {
            int views = code.getViews();
            if (views > 0) {
                code.setViews(--views);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");

            }
        }

        if (code.isHasTimeLimit()) {
            long timeDiff =
                    ChronoUnit.SECONDS.between(
                            LocalDateTime.parse(code.getDate(), formatter),
                            LocalDateTime.now());
            long timeLeft = code.getTime() - timeDiff;
            if (timeLeft > 0) {
                code.setTime(timeLeft);
                codeRepository.save(code);
            } else {
                codeRepository.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
            }
        }

        return code;
    }

    public void addSnippet(Code code) {
        codeRepository.save(code);
    }


    public List<Code> getTenLatest() {
        Iterable<Code> all = codeRepository.findAllByOrderByDateDesc();
        return StreamSupport.stream(all.spliterator(), false)
                .filter(code -> !code.isHasTimeLimit() && !code.isHasViewLimit())
                .limit(10).collect(Collectors.toList());
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
