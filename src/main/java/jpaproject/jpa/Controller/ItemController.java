package jpaproject.jpa.Controller;

import java.util.List;
import jpaproject.jpa.Controller.factory.ItemFactory;
import jpaproject.jpa.domain.item.Item;
import jpaproject.jpa.dto.UpdateItemDto;
import jpaproject.jpa.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemFactory itemFactory;


    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute ItemForm form) {
        System.out.println("=====================");
        System.out.println(form.getCategory());
        System.out.println("=====================");

        Item item = itemFactory.createItem(form);

        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);

        ItemForm form = itemFactory.viewItem(item);
        System.out.println("======================");
        System.out.println("form ==" + form.getCategory());
        System.out.println("======================");

        model.addAttribute("form", form);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") ItemForm form) {

        System.out.println("======================");
        System.out.println("getCategory:" + form.getCategory());
        System.out.println("form.getName:" + form.getName());
        System.out.println("form.getName:" + form.getPrice());
        System.out.println("form.getName:" + form.getStockQuantity());
        System.out.println("======================");

        UpdateItemDto dto = new UpdateItemDto();
        dto.setName(form.getName());
        dto.setPrice(form.getPrice());
        dto.setStockQuantity(form.getStockQuantity());
        dto.setAuthor(form.getAuthor());
        dto.setIsbn(form.getIsbn());
        dto.setArtist(form.getArtist());
        dto.setEtc(form.getEtc());
        dto.setDirector(form.getDirector());
        dto.setActor(form.getActor());

        itemService.updateItem(itemId, dto);

        return "redirect:/items";
    }


}

