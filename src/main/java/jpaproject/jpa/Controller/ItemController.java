package jpaproject.jpa.Controller;

import java.util.List;
import jpaproject.jpa.Controller.factory.ItemFactory;
import jpaproject.jpa.domain.item.Item;
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

        System.out.println("=====================");
        System.out.println("item = " + item);
        System.out.println("=====================");

        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        System.out.println("=====================");
        System.out.println("items = ");
        System.out.println("=====================");

        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        System.out.println("======================");
        System.out.println(item.getDtype());
        System.out.println();
        System.out.println("======================");

        ItemForm form = itemFactory.viewItem(item);
        System.out.println("======================");
        System.out.println("form ==" + form);
        System.out.println("======================");

        model.addAttribute("form", form);
        model.addAttribute("item", item);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") ItemForm form) {

//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }

}

