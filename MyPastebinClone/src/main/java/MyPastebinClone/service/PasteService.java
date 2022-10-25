package MyPastebinClone.service;

import MyPastebinClone.model.PasteModel;
import MyPastebinClone.repository.PasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PasteService {

    @Autowired
    private final PasteRepository pasteRepository;

    public PasteService(PasteRepository pasteRepository) {
        this.pasteRepository = pasteRepository;
    }

    public List<PasteModel> listAllPaste() {
        return pasteRepository.findAll();
    }

    public void save(PasteModel paste) {
        pasteRepository.save(paste);
    }

    public void deleteById(long paste_id) {
        pasteRepository.deleteById(paste_id);
    }

    public PasteModel getPasteById(long paste_id) {
        Optional<PasteModel> optional = pasteRepository.findById(paste_id);
        PasteModel paste;
        if (optional.isPresent()) {
            paste = optional.get();
        } else {
            throw new RuntimeException("Can not found the paste with paste_id: " + paste_id);
        }
        return paste;
    }
}
