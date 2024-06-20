import auth_actions from "@/store/actions/auth_actions";
import product_actions from "@/store/actions/product_actions";

export default {
  ...auth_actions,
  ...product_actions,
};