import { createSlice, PayloadAction } from "@reduxjs/toolkit";

export interface SearchState {
  term: string;
}

const initialState: SearchState = {
  term: "",
};

export const searchSlice = createSlice({
  name: "searchTerm",
  initialState: initialState,
  reducers: {
    termChange: (state, action: PayloadAction<string>) => {
      state.term = action.payload;
    },
  },
});

export const { termChange } = searchSlice.actions;
export const searchTermSelector = (state: {search: SearchState}) => state.search.term;

export default searchSlice.reducer;
