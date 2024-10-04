package com.hamitmizrak.springboot_ecommerce.controller.api.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.services.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }



    // POST - Create a new address
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto savedAddress = addressService.createAddress(addressDto);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    // GET - Find an address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        AddressDto addressDto = addressService.getAddressById(id);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    // GET - List all addresses
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // PUT - Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    // DELETE - Delete an address by ID
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
*/

    // DELETE - Delete an address by ID
    @DeleteMapping
    public ResponseEntity<Void> deleteAddress(@RequestParam Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*

    Aşağıda `@PathVariable` yerine `@RequestParam` kullanarak aynı silme işlemini nasıl yapabileceğinizi ve bu iki anotasyon arasındaki farkları.

### 1. **@RequestParam Kullanarak DELETE İşlemi**

`@RequestParam`'i kullanarak parametreyi sorgu parametresi (query parameter) olarak alabiliriz. Aşağıda, `@RequestParam`'in kullanıldığı örnek:

#### Güncellenmiş `deleteAddress` Metodu:
```java
// DELETE - Delete an address by ID
@DeleteMapping
public ResponseEntity<Void> deleteAddress(@RequestParam Long id) {
    addressService.deleteAddress(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
```

Bu versiyonda `id`, URL'in sonuna sorgu parametresi olarak eklenir, yani şu şekilde çağrılır:

```
DELETE /api/addresses?id=1
```

### 2. **@PathVariable ve @RequestParam Arasındaki Farklar**

#### 2.1. **@PathVariable**
- **Kullanım amacı**: `@PathVariable`, URL yolundaki (path) değişkenleri yakalamak için kullanılır. Örneğin, `/addresses/1` gibi bir URL'de `1` değerini yakalamak için kullanılır.
- **URL örneği**:
  ```
  DELETE /api/addresses/1
  ```
- **Kullanımı**: URL'nin bir parçası olan parametreleri alır. Parametreler URL yolunda bulunur ve genellikle RESTful servislerde kaynakları tanımlamak için kullanılır.

#### 2.2. **@RequestParam**
- **Kullanım amacı**: `@RequestParam`, URL'deki sorgu parametrelerini almak için kullanılır. Parametreler `?` işaretinden sonra gelir ve genellikle anahtar-değer çifti şeklindedir.
- **URL örneği**:
  ```
  DELETE /api/addresses?id=1
  ```
- **Kullanımı**: Sorgu parametrelerini alır. Parametreler URL'nin sonunda bulunur ve genellikle opsiyonel veriler veya filtreler için kullanılır. Parametreler bir veya daha fazla olabilir ve `?` işaretinden sonra belirtilir.

### Farklar:
1. **Yerleşim**:
   - `@PathVariable`: URL yolundaki parametreleri alır, yani `/resource/{id}` şeklinde kullanılır.
   - `@RequestParam`: URL'deki sorgu parametrelerini alır, yani `/resource?id=1` şeklinde kullanılır.

2. **Kullanım Alanı**:
   - `@PathVariable`: Daha çok RESTful mimaride, kaynaklara ulaşmak ve işlem yapmak için kullanılır. Örneğin, belirli bir id'ye sahip bir kaynağı silmek, güncellemek veya getirmek için kullanılır.
   - `@RequestParam`: Genellikle opsiyonel veya isteğe bağlı parametreler için kullanılır. Örneğin, bir listeyi belirli parametrelere göre filtrelemek.

3. **Esneklik**:
   - `@PathVariable`: URL'nin bir parçası olduğundan, parametrelerin sabit olması gerekir ve yapılandırılması zorunludur.
   - `@RequestParam`: Parametreler opsiyonel olabilir ve daha fazla esneklik sağlar, çünkü birden fazla parametre eklenebilir ve herhangi bir sırayla kullanılabilir.

### Hangi Durumda Hangisi Kullanılır?
- **@PathVariable**: Kaynağa özgü bir işlem yapılırken (örneğin, belirli bir kullanıcıyı, siparişi veya adresi bulma ya da silme) kullanılır.
- **@RequestParam**: Filtreleme, sıralama, arama gibi işlemler için birden fazla parametre ile çalışılacaksa kullanılır.

Özetle:
- `@PathVariable`: `DELETE /api/addresses/1` — belirli bir kaynağı silmek için daha uygun.
- `@RequestParam`: `DELETE /api/addresses?id=1` — genellikle isteğe bağlı parametreler için kullanılır.

Başka sorularınız varsa, memnuniyetle yardımcı olurum!

     */

}