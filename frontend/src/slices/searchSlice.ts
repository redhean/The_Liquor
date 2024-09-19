import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { SearchLiqourParams } from "@/services/liquor";
import { RootState } from "@/store";

const initialState: SearchLiqourParams = {
  term: "",
  page: 1,
  alcMin: 0,
  alcMax: 100,
  brand: "",
  class: "",
  avail: true,
};

export const searchSlice = createSlice({
  name: "searchTerm",
  initialState: initialState,
  reducers: {
    termChange: (state, action: PayloadAction<string>) => {
      state.term = action.payload;
    },
    pageChange: (state, action: PayloadAction<number>) => {
      state.page = action.payload;
    },
    minAlcChange: (state, action: PayloadAction<number>) => {
      state.alcMin = action.payload;
    },
    maxAlcChange: (state, action: PayloadAction<number>) => {
      state.alcMax = action.payload;
    },
    brandChange: (state, action: PayloadAction<string>) => {
      state.brand = action.payload;
    },
    classChange: (state, action: PayloadAction<string>) => {
      state.class = action.payload;
    },
    availChange: (state, action: PayloadAction<boolean>) => {
      state.avail = action.payload;
    },
    resetAll: (state) => {
      state = initialState;
    },
  },
});

export const {
  termChange,
  brandChange,
  classChange,
  maxAlcChange,
  minAlcChange,
  pageChange,
  availChange,
  resetAll
} = searchSlice.actions;

export default searchSlice.reducer;
