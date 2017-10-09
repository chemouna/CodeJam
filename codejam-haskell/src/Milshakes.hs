module Milshakes where

import Control.Monad
import Data.Maybe
import Data.Array

type Constraint = [(Int, Bool)] -- list of the flavors customers like and whether they are malted or not

-- get the input for a customer
get_customer :: IO Constraint
get_customer =
  let pc acc [] = acc
      pc acc (i:b:cs) = pc ((i - 1, b == 1) : acc) cs
  in
  do (_ : cs) <- liftM (map read . words) getLine
     return (pc [] cs)

show_arr :: Array Int Bool -> String
show_arr a = unwords [if b then "1" else "0" | b <- elems a]

solve :: Int -> [Constraint] -> Maybe (Array Int Bool)
solve = undefined

one_case :: Int -> IO ()
one_case i = do
  n <- liftM read getLine
  m <- liftM read getLine
  cs <- sequence (replicate m get_customer)
  putStrLn ("Case #" ++ show i ++ ": " ++ fromMaybe "NOTHING" (liftM show_arr (solve n cs)))

main :: IO ()
main = do
  c <-liftM read getLine
  sequence_ (map one_case [1..c])
