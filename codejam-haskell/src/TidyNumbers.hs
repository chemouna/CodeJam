{-# LANGUAGE TupleSections #-}

module TidyNumbers where

import Control.Monad (mapM_)
import Data.List (sort)

reduceToTidy :: String -> String
reduceToTidy [] = []
reduceToTidy (a : as) =
  if replicate (length as) a > as then pred a : replicate (length as) '9'
  else a : reduceToTidy as


solveInput = do
  s <- reduceToTidy <$> getLine
  return $ show $ (read s :: Integer)

main = do
  x <- readLn
  flip mapM [1 .. x] $ \c -> do
    result <- solveInput
    putStr ("Case #" ++ show c ++ ": " ++ result ++ "\n")


isTidy :: Integer -> Bool
isTidy n = let str = show n in str == sort str

largestTidy :: Integer -> Integer
largestTidy = read . td [] . reverse . show
  where td acc [] = acc
        td [] (x:xs) = td [x] xs
        td as@(a:_) (x:xs) | x <= a    = td (x:as) xs
                           | otherwise = td ((pred x):('9' <$ as)) xs

{--
main' = do t <- readLn :: IO Int
            flip  mapM_ [1..t] $ \c -> do
            n <- readLn
            putStr $ "Case #" ++ show c ++ ": "
            print $ largestTidy n

--}
