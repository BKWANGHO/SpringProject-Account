import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import userReducer from "@/redux/features/users/user.slice"
import createWebStorage from "redux-persist/lib/storage/createWebStorage";

const createNoopStorage = () => {
  return {
    getItem() {
      return Promise.resolve(null);
    },
    setItem(_key: string, value: number) {
      return Promise.resolve(value);
    },
    removeItem() {
      return Promise.resolve();
    },
  };
};

const storage =
  typeof window !== "undefined"
    ? createWebStorage("local")
    : createNoopStorage();


const countPersistConfig = {
  key: "user",
  storage,
  whitelist: ["userState"],
};


const persistedCountReducer = persistReducer(countPersistConfig, userReducer);

export const rootReducer = combineReducers({
  count: persistedCountReducer,
});