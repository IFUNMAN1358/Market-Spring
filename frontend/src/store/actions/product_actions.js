export default {
    viewProduct(productId) {
      this.$router.push({ name: 'ShowProductComponent', params: { id: productId } });
    },
    addToCart(product) {
      console.log(`Added to cart: ${product.productName}`);
    },
    goBackToCatalog() {
      this.$router.push({ name: 'CatalogComponent' });
    },
    editProduct(product) {
      console.log(`Edit product: ${product.productName}`);
      this.$router.push({ name: 'EditProductComponent', params: { id: product.id } });
    },
    deleteProduct(product) {
      console.log(`Delete product: ${product.productName}`);
    }
}