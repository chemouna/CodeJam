module MinScalarProduct where

import Control.Applicative
import Control.Monad
import Data.List

-- TODO: do the same in Idris but with the constraint on length of the vectors to be equal

minScalarProduct :: [Int] -> [Int] -> Int
minScalarProduct v1 v2 = minimum $ map (uncurry scalarProduct) $ permCombinations pv1 pv2
  where
    scalarProduct xs ys = sum $ zipWith (\x y -> x * y) xs ys
    permCombinations xs ys = [(x, y) | x <- xs , y <- ys]
    pv1 = filter (\x -> not (x == v1)) (permutations v1)
    pv2 =  filter (\x -> not (x == v2)) (permutations v2)

minScalarProduct2 :: [Int] -> [Int] -> Int
minScalarProduct2 xs ys = sum [x * y | (x, y) <- zip (sort xs) (reverse (sort ys))]

one_case :: Int -> IO ()
one_case i = do
  n <- liftM read getLine :: IO Int
  a <- liftM (map read . words) getLine
  b <- liftM (map read . words) getLine
  putStrLn ("Case #" ++ show i ++ ": " ++ show (minScalarProduct2 a b))

main :: IO ()
main = do
  t <- liftM read getLine
  sequence_ (map one_case [1 .. t])
