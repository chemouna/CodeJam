module MinScalarProduct where

import Data.List

-- TODO: do the same in Idris but with the constraint on length of the vectors to be equal

minScalarProduct :: [Int] -> [Int] -> Int
minScalarProduct v1 v2 = minimum $ map (uncurry scalarProduct) $ permCombinations pv1 pv2
  where
    scalarProduct xs ys = sum $ zipWith (\x y -> x * y) xs ys
    permCombinations xs ys = [(x, y) | x <- xs , y <- ys]
    pv1 = filter (\x -> not (x == v1)) (permutations v1)
    pv2 =  filter (\x -> not (x == v2)) (permutations v2)

