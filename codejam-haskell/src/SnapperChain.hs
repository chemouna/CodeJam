module SnapperChain where

import Data.Bits
import Data.Bool

type SnapperChain = [Bool]

-- A solution that is almost direct translation of the problem
snapOnOff :: SnapperChain -> SnapperChain
snapOnOff [] = []
snapOnOff (c:cs) =
    if isPowered cs
        then  snapOnOff cs ++ [toggleState c]
        else  snapOnOff cs ++ [c]

isPowered :: SnapperChain -> Bool
isPowered []     = True
isPowered (c:cs) = inOnState c && isPowered cs

inOnState = id
toggleState = not

showBinary :: SnapperChain -> String
showBinary = map (\b-> if b then '1' else '0')

-- take 4 $ iterate snapOnOff [False,False,False]

-- Simpler Solution 1
caseAlgorithm :: Int -> Int -> String
caseAlgorithm n k =
    if (((k+1) `mod` 2^n) == 0)
        then "ON"
        else "OFF"

-- Solution 2
solve :: Int -> Int -> String
solve n k = if k .&. a == a then "ON" else "OFF"
    where a = (2 ^ n) - 1



-- Another brute force solution by simulation

solve_brute n k = bool "OFF" "ON" $ and $ iterate snap (replicate n False) !! k

snap [] = []
snap (x:xs) = (x /= and xs) : snap xs

-- Solution 3
solve'' n k = bool "OFF" "ON" $ (k + 1) `mod` 2^n == 0
