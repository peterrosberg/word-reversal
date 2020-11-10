import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';
import reversalReducer from '../features/reversal/reversalSlice';

export default configureStore({
  reducer: {
    counter: counterReducer,
    reversal: reversalReducer,
  },
});
